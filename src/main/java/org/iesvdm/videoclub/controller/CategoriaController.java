package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.service.CategoriaService;
import org.iesvdm.videoclub.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar"})
    public List<Categoria> all() {
        log.info("Accediendo a todas las películas");
        return this.categoriaService.all();
    }

    @GetMapping(value = {"","/"})
    public List<Categoria> all(@RequestParam("buscar")Optional<String> buscarOptional, @RequestParam("ordenar")Optional<String> ordenarOptional){
        log.info("Accediendo a categoria especifica");

        if(buscarOptional.isPresent()){
            if(ordenarOptional.get().equals("asc")){
                return this.categoriaService.allwOrderA(buscarOptional.get());
            }else{
                return this.categoriaService.allwOrderD(buscarOptional.get());
            }
        }else{
            if(ordenarOptional.get().equals("asc")){
                return this.categoriaService.allwOrderA();
            }else{
                return this.categoriaService.allwOrderD();
            }
        }
    }

    @GetMapping(value = {"/pagination","/pagination/"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam("buscar")Optional<String> buscarOptional, @RequestParam("ordenar")Optional<String> ordenarOptional,
                                         @RequestParam(value="tamanio", defaultValue="0") int pagina, @RequestParam(value = "tamanio", defaultValue = "3")int tamanio){
        log.info("Accediendo a categoria especifica ordenada paginada");
        Map<String, Object> responseAll = this.categoriaService.all(pagina, tamanio, buscarOptional.get(), ordenarOptional.get());
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = {"/pc"})
    public List<Integer> pc(){
        log.info("peliculas por categoria");
        return this.categoriaService.peliculasCategoria();
    }

    @GetMapping(value = {"/pcn"})
    public List<Object[]> pcn(){
        log.info("peliculas por categoria con nombre");
        return this.categoriaService.peliculasCategoriaWNombre();
    }

    @PostMapping({"","/"})
    public Categoria newCategoria(@RequestBody Categoria categoria) {
        return this.categoriaService.save(categoria);
    }

    @GetMapping("/{id}")
    public Categoria one(@PathVariable("id") Long id) {
        return this.categoriaService.one(id);
    }

    @PutMapping("/{id}")
    public Categoria replaceCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        return this.categoriaService.replace(id, categoria);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable("id") Long id) {
        this.categoriaService.delete(id);
    }
}
