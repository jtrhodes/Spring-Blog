package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Dice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RollController {
    @GetMapping("/roll-dice")
    public String dice(){
        return "/dice-roll";
    }

    @GetMapping("/roll-dice/{}")
    @ResponseBody
    public void diceRoller(){
        Dice all = new Dice();
        all.setD4((int) Math.floor(Math.random() *4 +1));
        all.setD6((int) Math.floor(Math.random() * 6 + 1));
        all.setD8((int) Math.floor(Math.random() * 8 + 1));
    }

}
// if (roast.equals("dark")){
//        selection.setOrigin("Colombia");
//        selection2.setOrigin("Brazil");
//    } else if (roast.equals("medium")){
//        selection.setOrigin("New Guinea");
//        selection2.setOrigin("Sumatra");
//    } else {
//        selection.setOrigin("Kenya");
//        selection2.setOrigin("Ethiopia");
//    }
//    List<Coffee> selections = new ArrayList<>();
//        selections.add(selection);
//        selections.add(selection2);
//        model.addAttribute("roast", roast);
//        model.addAttribute("selections", selections);
//        return "views-lec/coffee";
//}