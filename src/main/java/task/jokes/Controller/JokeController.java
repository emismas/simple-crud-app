package task.jokes.Controller;

import com.sun.corba.se.impl.ior.JIDLObjectKeyTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import task.jokes.Domain.Category;
import task.jokes.Domain.Joke;
import task.jokes.Service.CategoryService;
import task.jokes.Service.JokeService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.security.Principal;
import java.text.NumberFormat;
import java.util.List;

@Controller
public class JokeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JokeService jokeService;

    private List<Category> categories;
    private Pageable pageable = new PageRequest(0, 5);
    @GetMapping("/")
    public String index(Model model, Pageable pageableTemp, Principal principal, @ModelAttribute("keepPagination") String keepPagination) {
        if(!keepPagination.isEmpty() && Integer.parseInt(keepPagination) == 1) {
            System.out.println(pageable.getPageNumber());
        } else {
            pageable = new PageRequest(pageableTemp.getPageNumber(), 5);
        }
        categories = categoryService.getAllCategories();
        Page<Joke> jokePage = jokeService.getJokesSortedByLikeDislikeDiff(pageable);
        PageWrapper<Joke> page = new PageWrapper<>(jokePage, "/");
        List<Joke> jokes = jokePage.getContent();
        for(Joke joke: jokes) {
            joke.setCategory(categoryService.getCategoryById(joke.getCategoryid()));
        }
        model.addAttribute("jokes", jokePage.getContent());
        model.addAttribute("page", page);

        if (principal != null)
            model.addAttribute("username", principal.getName());

        return "index";
    }

    @GetMapping("/new")
    public String newJoke(Model model, Principal principal) {
        model.addAttribute("categories", categories);
        model.addAttribute("joke", new Joke());

        if (principal != null)
            model.addAttribute("username", principal.getName());

        return "unos";
    }

    @GetMapping("/like/{id}")
    public String likePost(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        jokeService.likePost(Integer.parseInt(id));
        redirectAttributes.addFlashAttribute("keepPagination", 1);

        return "redirect:/";
    }

    @GetMapping("/dislike/{id}")
    public String dislikePost(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        jokeService.dislikePost(Integer.parseInt(id));
        redirectAttributes.addFlashAttribute("keepPagination", 1);

        return "redirect:/";
    }

    @PostMapping("/new")
    public String postJoke(@ModelAttribute(value = "joke") @Valid Joke joke, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            if (principal != null)
                model.addAttribute("username", principal.getName());
            model.addAttribute("categories", categories);
            return "unos";
        }

        jokeService.saveJoke(joke);

        return "redirect:/";
    }
}