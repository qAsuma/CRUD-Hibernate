package solo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import solo.dao.AccountDAO;
import solo.models.Account;
import solo.models.Client;

@Controller
@RequestMapping("acc")
public class AccountController {

    private final AccountDAO accountDAO;


    @Autowired
    public AccountController(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;

    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("acc", accountDAO.index());
        return "indexAccount";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("client") Client client) {
        model.addAttribute("account", accountDAO.show(id));


        return "showAccount";
    }



    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("account", accountDAO.show(id));
        return "editAccount";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("account") Account account, BindingResult bindingResult,
                         @PathVariable("id") int id) {
//        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors())
            return "editAccount";

        accountDAO.update(id, account);
        return "redirect:/acc";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        accountDAO.delete(id);
        return "redirect:/acc";
    }


}
