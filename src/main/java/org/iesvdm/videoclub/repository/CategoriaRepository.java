package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    /*
    @Query(value= "select p from Pelicula p join p.categorias c where c.nombre like %:categoria%")
    public List<Pelicula> queryPeliculaContieneCategoria(@Param("categoria") String categoria);
     */

    @Query(value= "select count(p) from Categoria c join c.peliculas p group by c")
    public List<Integer> queryPeliculasPorCategoria();

    @Query(value= "select c, count(p) from Categoria c join c.peliculas p group by c")
    public List<Object[]> queryPeliculasPorCategoriaWNombre();

    @Query(value="select c from Categoria c where c.nombre like %:buscar%")
    public List<Categoria> queryCategoriaConNombre(@Param("buscar") String buscar);

    public List<Categoria> findAllByOrderByIdAsc();
    public List<Categoria> findAllByOrderByIdDesc();

    public List<Categoria> findByNombreContainingIgnoreCaseOrderByIdAsc(String nombre);
    public List<Categoria> findByNombreContainingIgnoreCaseOrderByIdDesc(String nombre);
}
