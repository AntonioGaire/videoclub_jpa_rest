package org.iesvdm.videoclub.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.exception.CategoriaNotFoundException;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PageRanges;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }

    public List<Categoria> all(String buscar) {return this.categoriaRepository.queryCategoriaConNombre(buscar);}
    public List<Categoria> allwOrderA() {return this.categoriaRepository.findAllByOrderByIdAsc();}
    public List<Categoria> allwOrderD() {return this.categoriaRepository.findAllByOrderByIdDesc();}
    public List<Categoria> allwOrderA(String nombre) {return this.categoriaRepository.findByNombreContainingIgnoreCaseOrderByIdAsc(nombre);}
    public List<Categoria> allwOrderD(String nombre) {return this.categoriaRepository.findByNombreContainingIgnoreCaseOrderByIdDesc(nombre);}

    public List<Integer> peliculasCategoria() {return this.categoriaRepository.queryPeliculasPorCategoria();}

    public List<Object[]> peliculasCategoriaWNombre() {return this.categoriaRepository.queryPeliculasPorCategoriaWNombre();}
    public Categoria save(Categoria categoria) {
        categoria.setUltimaActualizacion(new Date());
        return this.categoriaRepository.save(categoria);
    }

    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria replace(Long id, Categoria categoria) {

        return this.categoriaRepository.findById(id).map( p -> (id.equals(categoria.getId())  ?
                        this.categoriaRepository.save(categoria) : null))
                .orElseThrow(() -> new CategoriaNotFoundException(id));

    }

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(p -> {this.categoriaRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Map<String, Object> all(int pagina, int tamanio, String buscar, String ordenar){
        if(ordenar.toLowerCase().equals("desc")){
            Pageable pages = PageRequest.of(pagina, tamanio, Sort.by("idPelicula").descending());
        }else{
            Pageable pages = PageRequest.of(pagina, tamanio, Sort.by("idPelicula").ascending());
        }

        Page<Categoria> categoriaPage = this.categoriaRepository.findByNombreContainingIgnoreCaseOrderByIdAsc(buscar);

        Map<String, Object> response = new HashMap<>();

        response.put("categorias", categoriaPage.getContent());
        response.put("currentPage", categoriaPage.getNumber());
        response.put("totalItems", categoriaPage.getTotalElements());
        response.put("totalPages", categoriaPage.getTotalPages());
    }
}
