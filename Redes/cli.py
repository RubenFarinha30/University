import socket
import time
import os

# Configurações do servidor
HOST = 'localhost'  # Endereço IP do servidor
PORT = 5555  # Porta utilizada pelo servidor

# Mensagem de boas-vindas com a lista de comandos
welcome_message = """
Bem-vindo ao Servidor!

- IAM <aluno_id> <password>:
  Comando para identificação do aluno e registo de presença.
  Utilize junto com a sua password para autenticação.

- ASK <pergunta>:
  Comando para fazer uma pergunta ao servidor.

- ANSWER <question_id> <resposta>:
  Comando para adicionar uma resposta a uma pergunta específica.

- LISTQUESTIONS:
  Comando para listar todas as perguntas feitas.

- PUTFILE:
  Comando para enviar/upload de um arquivo para o servidor.

- LISTFILES:
  Comando para mostrar os arquivos disponíveis no servidor.

- GETFILE <nome_do_arquivo>:
  Comando para download/receber um arquivo do servidor.

- EXIT:
  Desconectar do servidor

O servidor realiza a persistência de dados a cada 5 minutos. Guardando os dados num ficheiro .pickle

Digite um comando para interagir com o servidor.
"""

# Função para enviar o arquivo
def send_file(client_socket, filename):
    try:
        if not os.path.exists(filename):
            return "O arquivo especificado não existe."

        file_size = os.path.getsize(filename)

        command = f"PUTFILE {filename} {file_size}"
        client_socket.send(command.encode())

        response = client_socket.recv(1024).decode()

        if not response.startswith("READY"):
            return response

        with open(filename, "rb") as file:
            for _ in range(file_size):
                data = file.read(1024)
                client_socket.sendall(data)

        response = client_socket.recv(1024).decode()

        return response

    except Exception as e:
        return str(e)

# Função para receber o arquivo
def receive_file(client_socket, filename, file_size):
    try:
        # Abre o arquivo para escrita
        with open(filename, "wb") as file:
            bytes_received = 0

            # Recebe os dados do arquivo em partes
            while bytes_received < file_size:
                data = client_socket.recv(1024)
                file.write(data)
                bytes_received += len(data)

        return f"Arquivo {filename} recebido com sucesso."

    except Exception as e:
        return str(e)
    
# Criação do socket TCP do cliente
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)   

try:
    # Conecta ao servidor
    client_socket.connect((HOST, PORT))
    print("Conexão estabelecida com o servidor.")
    print(welcome_message)

    # Loop principal do cliente
    while True:
        command = input("Digite um comando: ")

        if command.startswith("PUTFILE"):
            parts = command.split()
            filename = parts[1]
            response = send_file(client_socket, filename)
            print(response)
            continue
        

        if command.startswith("GETFILE"):
          parts = command.split()
          file_id = int(parts[1])
          
          client_socket.send(command.encode())

          response = client_socket.recv(1024).decode()

          if response.startswith("FILE"):
              response_parts = response.split()
              file_id = response_parts[1]
              filename = response_parts[2]
              file_size = int(response_parts[3])

              response = receive_file(client_socket, filename, file_size)
              print(response)
              continue
          else:
              print(response)
              continue
          

        client_socket.send(command.encode())

        time.sleep(0.1)

        response = client_socket.recv(1024).decode()

        print(response)

except ConnectionRefusedError:
    print("Não foi possível conectar ao servidor. Certifique-se de que o servidor está em execução.")

finally:
    client_socket.close()
