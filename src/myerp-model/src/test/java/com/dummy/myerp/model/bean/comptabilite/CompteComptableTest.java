package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class CompteComptableTest {

    private List<CompteComptable> compteComptableList;

    @Before
    public void initCompteComptable(){
        compteComptableList = new ArrayList<CompteComptable>();
        compteComptableList.add(new CompteComptable(1234,"Compte courant 1"));
        compteComptableList.add(new CompteComptable(12345, "Compte courant 2"));

    }

    @Test

    public void getByNumero_whenCompteComptableExist(){
        assertThat(CompteComptable.getByNumero(compteComptableList, 1234).getLibelle()).isEqualTo("Compte courant 1");
    }

    @Test
    public void getByNumero_whenCompteComptableNotExist(){
        assertThat(CompteComptable.getByNumero(compteComptableList, 12)).isEqualTo(null);

    }

    @Test
    public void test_GettterSetter_CompteComptable(){
        CompteComptable compteComptable = new CompteComptable();
        compteComptable.setNumero(123456);
        compteComptable.setLibelle("compte test");
        assertThat(compteComptable.getLibelle()).isEqualTo("compte test");
        assertThat(compteComptable.getNumero()).isEqualTo(123456);
    }

    @Test
    public void testToString(){

        assertThat(compteComptableList.get(1).toString()).isEqualTo("CompteComptable{numero=12345, libelle='Compte courant 2'}");

    }
}
