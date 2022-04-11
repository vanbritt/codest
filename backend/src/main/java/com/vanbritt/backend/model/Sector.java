package com.vanbritt.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "sectors")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2,message = "Name Should be atleast 2 characters")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonManagedReference("self-reference")
    private Sector parentSector;

    @OneToMany(mappedBy = "parentSector", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Sector> subSectors = new HashSet<>();

//    @ManyToMany(mappedBy = "sectors", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<Entry> entries = new HashSet<>();


   public Sector addSubSector(Sector subSector) {
        if(this.subSectors==null)
            this.subSectors = new HashSet<>();
        this.subSectors.add(subSector);
        subSector.setParentSector(this);
        return subSector;
    }

    public Sector addParentSector(Sector parentSector){
       this.setParentSector(parentSector);
       this.getParentSector().addSubSector(this);
       return parentSector;
    }


}
