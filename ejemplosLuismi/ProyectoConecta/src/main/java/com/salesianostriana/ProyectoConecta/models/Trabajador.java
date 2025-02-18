package com.salesianostriana.ProyectoConecta.models;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@SuperBuilder
@SQLDelete(sql = "UPDATE trabajador SET borrado = true WHERE id=?")
@FilterDef(name = "trabajadorBorradoFiltro", parameters = @ParamDef(name = "isBorrado", type = Boolean.class))
@Filter(name = "trabajadorBorradoFiltro", condition = "borrado = :isBorrado")
public class Trabajador extends Persona{

    private String puesto;

    private String area;

    private boolean borrado = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.EAGER)
    private Empresa empresa;



    public void addEmpresa(Empresa empresa){
        this.empresa = empresa;
        empresa.getListaTrabajadores().add(this);
    }
    public void removeEmpresa(Empresa empresa){
        empresa.getListaTrabajadores().remove(this);
        this.empresa = null;
    }

}
