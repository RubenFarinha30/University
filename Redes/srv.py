import socket
import os
import time
import threading
import pickle

# Configurações do servidor
HOST = 'localhost'
PORT = 5555

# Dicionários para armazenar os dados do sistema
presencas = {}
questions = {}
files = {}
passwords = {}

# Mutex para sincronização de acesso aos dados
mutex = threading.Lock()

# Função para calcular a presença com base no tempo de atraso
def calcular_presenca(atraso):
    if atraso < 20 * 60:
        return "Completa"
    elif atraso >= 20 * 60 and atraso < 45 * 60:
        return "Meia presença"
    else:
        return "Presença não registada"

# Função para persistência de dados
def persistir_dados():
    while True:
        time.sleep(300)  # intervalo 5 minutos

        with mutex:
            with open("dados.pickle", "wb") as file:
                pickle.dump((presencas, questions, files, passwords), file)

# Carregar os dados do arquivo de persistência, se existir
if os.path.exists("dados.pickle"):
    with open("dados.pickle", "rb") as file:
        presencas, questions, files, passwords = pickle.load(file)

# Iniciar a thread para a persistência dos dados
persist_thread = threading.Thread(target=persistir_dados)
persist_thread.daemon = True
persist_thread.start()

# Função que processa os comandos recebidos
def processar_comando(command, client_socket, authenticated):
    global presencas
    global questions
    global files
    global passwords

    if not authenticated:
        if command.startswith("IAM"):
            parts = command.split()
            aluno_id = parts[1]
            if len(parts) == 3:
                password = parts[2]
            else:
                response = "Escreva uma senha!"
                client_socket.send(response.encode())
                return

            with mutex:
                if aluno_id in passwords:
                    if passwords[aluno_id] == password:
                        atraso = time.time() - start_time

                        presenca = calcular_presenca(atraso)

                        presencas[aluno_id] = presenca

                        response = f"HELLO {aluno_id} - PRESENCA: {presenca}"
                        client_socket.send(response.encode())
                        authenticated = True
                    else:
                        response = "Senha incorreta, tente novamente"
                        client_socket.send(response.encode())
                else:
                    if password:
                        passwords[aluno_id] = password
                        response = f"Senha definida com sucesso para o aluno {aluno_id}, faça login novamente com a sua senha."
                    else:
                        response = "Deve definir uma senha"

                    client_socket.send(response.encode())

        else:
            # Cliente não autenticado, enviar mensagem de erro
            response = "Autenticação requerida. Por favor, faça o login (comando IAM)."
            client_socket.send(response.encode())

    elif command.startswith("ASK"):
        pergunta = " ".join(command.split()[1:])

        with mutex:
            question_id = len(questions) + 1

            questions[question_id] = {
                "pergunta": pergunta,
                "resposta": None
            }

            response = f"QUESTION_ID: {question_id}"
            client_socket.send(response.encode())

    elif command.startswith("ANSWER"):

        try:
            _, question_id, resposta = command.split(" ", 2)
            question_id = int(question_id)
            with mutex:
                if question_id in questions:
                    if questions[question_id]["resposta"]:
                        questions[question_id]["resposta"].append(resposta)
                    else:
                        questions[question_id]["resposta"] = [resposta]

                    response = f"Resposta registada para a pergunta {question_id}"
                else:
                    response = f"Pergunta com ID {question_id} não encontrada"
        except ValueError:
            response = "Comando inválido. O ID da pergunta deve ser um número inteiro."

        client_socket.send(response.encode())



    elif command.startswith("LISTQUESTIONS"):
        with mutex:
            response = "LISTQUESTIONS\n"
            if not questions:
                response += "Não há perguntas na lista.\n"
            else:
                for question_id, question_data in questions.items():
                    pergunta = question_data["pergunta"]
                    respostas = question_data["resposta"]
                    response += f"({question_id}) {pergunta}\n"

                    if respostas:
                        response += "\n".join(respostas) + "\n"
                    else:
                        response += "(NOTANSWERED)\n"
            response += "ENDQUESTIONS"
            client_socket.send(response.encode())



    elif command.startswith("PUTFILE"):
        parts = command.split()
        filename = parts[1]
        file_size = int(parts[2])

        content = b""
        bytes_received = 0

        while bytes_received < file_size:
            data = client_socket.recv(1024)
            content += data
            bytes_received += len(data)

        with mutex:
            files[filename] = {
                "size": file_size,
                "content": content
            }
        
        with open(filename, 'wb') as file:
            file.write(content)

        response = f"UPLOADED {filename}"
        client_socket.send(response.encode())

    elif command.startswith("LISTFILES"):
        with mutex:
            response = "LISTFILES\n"
            for file_id, (filename, file_data) in enumerate(files.items(), start=1):
                file_size = file_data["size"]
                response += f"({file_id}) {filename} - {file_size} bytes\n"
            response += "ENDFILES"

            client_socket.send(response.encode())

    elif command.startswith("GETFILE"):
        file_id = int(command.split()[1])

        with mutex:
            if file_id in range(1, len(files) + 1):
                filename, file_data = list(files.items())[file_id - 1]
                file_size = file_data["size"]
                file_content = file_data["content"]

                response = f"FILE {file_id} {filename} {file_size}"
                client_socket.send(response.encode())
                client_socket.sendall(file_content)
            else:
                response = f"File {file_id} not found"
                client_socket.send(response.encode())

    else:
        response = "Comando inválido"
        client_socket.send(response.encode())

    return authenticated

def handle_client(client_socket):
    authenticated = False
    try:
        while True:
            data = client_socket.recv(1024).decode()
            if not data:
                break

            if data.startswith("EXIT"):
                response = "Desconectado do servidor"
                client_socket.send(response.encode())
                print(f"Cliente {addr} saiu do servidor")
                break
            authenticated = processar_comando(data, client_socket, authenticated)
    except OSError as e:
        print(f"Erro durante a comunicação com o cliente {addr}: {str(e)}")

    client_socket.close()

# Criação do socket TCP do servidor
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind((HOST, PORT))
server_socket.listen(5)
start_time = time.time()

print("Servidor TCP iniciado. Aguardando conexões...")

while True:
    client_socket, addr = server_socket.accept()
    print(f"Cliente conectado: {addr}")

    # Cria uma nova thread para lidar com o cliente
    client_thread = threading.Thread(target=handle_client, args=(client_socket,))
    client_thread.start()