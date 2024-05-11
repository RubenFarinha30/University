package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.controller;

import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.dao.UserDao;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model.Evento;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.dao.EventoDao;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model.Atleta;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.dao.AtletaDao;
import org.springframework.security.core.Authentication;
import org.json.JSONObject;

@Controller
public class SpringSecurityController {

    @Autowired
    private EventoDao EventoDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AtletaDao AtletaDao;
    
    
    @GetMapping("/")
    public String defaultPage(Model model) {

        List<Evento> EventoList = EventoDao.getAllEventos();
        model.addAttribute("EventoList", EventoList);
        model.addAttribute("msg", "Run 4 Fun");

        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        
        return "index";
    }

    
    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("error", "Credenciais Inválidas");
            return "login";
        }
        if (logout != null) {
            model.addAttribute("msg", "A sua sessão foi encerrada com sucesso");
            return "login";
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model, @RequestParam(value = "idEvento", required = false) Integer idEvento,
            @RequestParam(value = "amount", required = false) Float amount,
            @RequestParam(value = "EventoDorsal", required = false) Integer EventoDorsal) {
    	
        if (idEvento != null && amount == null) {
            Atleta P = AtletaDao.getAtleta(idEvento, EventoDorsal);
            P.setPaystatus(true);
            AtletaDao.pay(P);
            model.addAttribute("message", "Pago com Sucesso");
            model.addAttribute("showpaybt", false);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        List<Atleta> pList = AtletaDao.getregisterusername(username);
        List<String> nomes = new ArrayList<String>();
        List<Float> valor = new ArrayList<Float>();

        for (Atleta Atleta : pList) {
            nomes.add(EventoDao.EventoName(Atleta.getIdEvento()));
            valor.add(EventoDao.EventoGetPrice(Atleta.getIdEvento()));
        }

        model.addAttribute("pList", pList);
        model.addAttribute("nList", nomes);
        model.addAttribute("aList", valor);

        if (idEvento != null && amount != null) {
            Atleta p = AtletaDao.getAtleta(idEvento, EventoDorsal);
            model.addAttribute("message", "Entidade:" + p.getEntity()
                    + "\n Referência:" + p.getRef() + "\n Quantia:" + EventoDao.EventoGetPrice(p.getIdEvento()) + "€");
            model.addAttribute("idEvento", p.getIdEvento());
            model.addAttribute("EventoDorsal", p.getDorsal());
            model.addAttribute("showpaybt", true);
        }

        return "userinscricao";
    }

    @GetMapping("/index")
    public String userPage(Model model, @RequestParam(value = "EventoName", required = false) String name,
            @RequestParam(value = "EventoDate", required = false) String date,
            @RequestParam(value = "type", required = false) String type) throws ParseException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);

        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"));

        model.addAttribute("isUser", isUser);

        String username = authentication.getName();

        if (username != null) {
            model.addAttribute("msg", "Bem-Vindo, " + username);
        }
        
        List<Evento> EventoList;
        if ((name == null && date == null) || (name.isEmpty() && date.isEmpty())) {
            EventoList = EventoDao.getAllEventos();
        } else {
            EventoList = EventoDao.searchEventos(name, date);
            if (type != null) {
                EventoList.retainAll(EventoDao.searchTypeEventos(type));
            }
        }
        model.addAttribute("EventoList", EventoList);


        return "index";
    }

    @GetMapping("/logout")
    public String logoutPage(Model model, HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login?logout";
    }

    @GetMapping("/admin")
    public String adminPage(Model model,
                            @RequestParam(value = "EventoName", required = false) String eventoName,
                            @RequestParam(value = "EventoDate", required = false) Date date,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "price", required = false) Float price,
                            @RequestParam(value = "Timestamp", required = false) Timestamp timestamp,
                            @RequestParam(value = "EventoID", required = false) Integer eventoId,
                            @RequestParam(value = "EventoDorsal", required = false) Integer eventoDorsal,
                            @RequestParam(value = "local", required = false) String local) {

        if (eventoName != null) {
            try {
                Evento e = new Evento(0, eventoName, date, price, description);
                EventoDao.saveEvento(e);
                model.addAttribute("msg", "Evento Salvo com Sucesso");
            } catch (NumberFormatException e) {
                model.addAttribute("msg", "Erro ao converter preço para número.");
            }
        }

        if (local != null) {
            try {
                AtletaDao.saveTime(eventoDorsal, eventoId, local, timestamp);
                model.addAttribute("msg", "Evento Salvo com Sucesso");
            } catch (NumberFormatException e) {
                model.addAttribute("msg", "Erro ao converter valores para números.");
            }
        }

        model.addAttribute("title", "Página de Controlo");

        return "admin";
    }


    @GetMapping("/newuser")
    public String newuser(Model model) {
        model.addAttribute("title", "Registo");
        model.addAttribute("message", "Preencha as suas informações");
        return "newuser";
    }


    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email1,
                           @RequestParam String email2,
                           Model model) {

        User existingUser = userDao.getUser(username);

        if (existingUser != null) {
            model.addAttribute("title", "Registo");
            model.addAttribute("message", "Preencha as suas informações");
            model.addAttribute("error", "O nome de utilizador já existe. Por favor, escolha outro.");
            return "newuser";
        }
        if (!email1.equals(email2)) {
            model.addAttribute("title", "Registo");
            model.addAttribute("message", "Preencha as suas informações");
            model.addAttribute("error", "Os emails são diferentes. Por favor, corrija.");
            return "newuser";
        }
        model.addAttribute("message", "O registo foi efetuado com sucesso.");
        model.addAttribute("title", "Página de Registo");

        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        User newUser = new User(username, encodedPassword, email1, "USER");
        userDao.saveUser(newUser);

        System.out.println("Gravado na BD: " + newUser.toString());

        model.addAttribute("user", newUser);

        return "login";
    }
    
    @GetMapping("/inscricao")
    public String inscricao(Model model,
            @RequestParam Integer Evento) {

        Evento Evento1 = EventoDao.getEvento(Evento);
        model.addAttribute("Evento", Evento1.getEname());
        model.addAttribute("Eventoid", Evento);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("USER"))) {

            String userauth = authentication.getName();
            model.addAttribute("username", userauth);

            return "inscricao";

        }
        return "redirect:/login?Evento=" + Evento;
    }

    @GetMapping("/userpage")
    public String Atleta(Model model, @RequestParam(required = false) Integer Evento,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String escalao,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String nif) {
        Integer int_nif = 0;
        try {
            int_nif = Integer.parseInt(nif);
        } catch (Exception e) {
            model.addAttribute("msg", "Valores Inválidos!(Não númerico)");
            return "inscricao";
        }

        Integer dorsal = AtletaDao.countDorsal(Evento);
        Atleta p = new Atleta(dorsal, Evento, username, nome, int_nif, genero, escalao, false,
                null, null, null, null, null, 0, 0, 0);
        AtletaDao.saveAtleta(p);
        return "redirect:/detailsPage?EventoId=" + Evento + "&EventoDorsal=" + dorsal + "&ok";
    }

    @GetMapping("/detailsPage")
    public String detalhes(Model model, @RequestParam(required = false) Integer EventoId,
            @RequestParam(required = false) Integer EventoDorsal,
            @RequestParam(value = "ok", required = false) String ok) {
    	
    	
        if (ok != null) {

            JSONObject obj = getReferenciaMB(EventoDao.EventoGetPrice(EventoId));
            String ent = obj.getString("mb_entity");
            String ref = obj.getString("mb_reference");
            String amount = obj.getString("mb_amount");

            model.addAttribute("msg", "Successfully registered as an Atleta<br>Entidade:" + ent
                    + "<br>Referência:" + ref + "<br>Quantia:" + amount + "€");

            AtletaDao.payment(ent, ref, EventoDorsal, EventoId);

        }

        boolean Eventodata = EventoDao.EventoDate(EventoId);
        model.addAttribute("EventoDate", Eventodata);

        List<Atleta> AtletaList = AtletaDao.getAllAtletas(EventoId);
        model.addAttribute("pList", AtletaList);
        for (Atleta Atleta : AtletaList) {
            if (Atleta.getStart() != null && Atleta.getFinish() != null) {
                long timeDifference = calculateTimeDifference(Atleta.getStart(), Atleta.getFinish());
                Atleta.setTtime(timeDifference);
                AtletaDao.saveTotalTime(Atleta);
            }
        }
        List<Atleta> tList = AtletaDao.getAllAtletasByTime(EventoId);
        model.addAttribute("tList", tList);
        model.addAttribute("EventoId", EventoId);

        String Eventoname = EventoDao.EventoName(EventoId);

        model.addAttribute("Eventoname", Eventoname);

        if (EventoDorsal != null && ok == null) {
            handleIndividualAtletaStatus(model, EventoId, EventoDorsal);
        }
        return "detailsPage";
    }
    
    private void handleIndividualAtletaStatus(Model model, Integer EventoId, Integer EventoDorsal) {
        Atleta P = AtletaDao.getAtleta(EventoId, EventoDorsal);
        String answer = "";

        if (P != null) {
            if (P.getTtime() != 0) {
                answer = "Atleta: " + P.getNomeAtleta() + " , terminou com tempo: " + P.getTtime() + "m.";
            } else if (P.getStart() == null) {
                answer = "Atleta: " + P.getNomeAtleta() + " , não começou a Prova ainda.";
            } else {
                handleIntermediateCheckpointStatus(P, answer);
            }
        } else {
            answer = "Nenhum Atleta com o dorsal indicado.";
        }

        model.addAttribute("statusMessage", answer);
    }

    private void handleIntermediateCheckpointStatus(Atleta atleta, String answer) {
        if (atleta.getP3() != null) {
            long timeDifference = calculateTimeDifference(atleta.getStart(), atleta.getP3());
            answer = "Atleta: " + atleta.getNomeAtleta() + " , passou em P3 com tempo de Prova: " + timeDifference + "m.";
        } else if (atleta.getP2() != null) {
            long timeDifference = calculateTimeDifference(atleta.getStart(), atleta.getP2());
            answer = "Atleta: " + atleta.getNomeAtleta() + " , passou em P2 com tempo de Prova: " + timeDifference + "m.";
        } else if (atleta.getP1() != null) {
            long timeDifference = calculateTimeDifference(atleta.getStart(), atleta.getP1());
            answer = "Atleta: " + atleta.getNomeAtleta() + " , passou em P1 com tempo de Prova: " + timeDifference + "m.";
        }
    }

    public static long calculateTimeDifference(Timestamp start, Timestamp end) {
        long timeDifferenceMillis = end.getTime() - start.getTime();
        return timeDifferenceMillis / (60 * 1000);
    }


    public static JSONObject getReferenciaMB(float amount) {
        try {
            URL url = new URL("https://magno.di.uevora.pt/tweb/t2/mbref4payment");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            String postData = "amount=" + amount;
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = postData.getBytes();
                os.write(input, 0, input.length);
            }

            StringBuilder result = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null;) {
                    result.append(line);
                }
            }
            System.out.println(result);
            return new JSONObject(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
