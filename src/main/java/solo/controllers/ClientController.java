package solo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import solo.dao.AccountDAO;
import solo.dao.ClientDAO;
import solo.models.Account;
import solo.models.Client;


@Controller
@RequestMapping("/people")
public class ClientController {

    private final ClientDAO clientDAO;
    private final AccountDAO accountDAO;

    @Autowired
    public ClientController(ClientDAO clientDAO, AccountDAO accountDAO) {
        this.clientDAO = clientDAO;
        this.accountDAO = accountDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", clientDAO.index());
        return "/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientDAO.show(id));
        return "/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("client") Client client, @ModelAttribute("account")Account account) {
        return "/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("client") Client client,@ModelAttribute("account")Account account,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/new";

        clientDAO.save(client);
        accountDAO.save(account);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("client", clientDAO.show(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("client") Client client, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "edit";

        clientDAO.update(id, client);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        clientDAO.delete(id);
        return "redirect:/people";
    }
}