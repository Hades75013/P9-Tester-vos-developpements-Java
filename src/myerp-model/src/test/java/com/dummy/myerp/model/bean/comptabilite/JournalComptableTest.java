package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JournalComptableTest {

    private List<JournalComptable> journalComptables;

    @Before
    public void initJournalComptable(){
        journalComptables = new ArrayList<JournalComptable>();
        journalComptables.add(new JournalComptable("AC","Achat"));
        journalComptables.add(new JournalComptable("DZ","Vente"));


    }

    @Test
    public void getByCode_whenJournalComptableExist(){
        assertThat(JournalComptable.getByCode(journalComptables,"AC").getLibelle()).isEqualTo("Achat");
    }

    @Test
    public void getByCode_whenJournalComptableNotExist(){
        assertThat(JournalComptable.getByCode(journalComptables, "AS")).isEqualTo(null);
    }

    @Test
    public void testGetterSetterJournalComptable(){
        JournalComptable pJournalComptable = new JournalComptable();
        pJournalComptable.setCode("AC");
        pJournalComptable.setLibelle("Achat");
        assertThat(pJournalComptable.getCode()).isEqualTo("AC");
        assertThat(pJournalComptable.getCode()).isNotEqualTo("BC");
        assertThat(pJournalComptable.getLibelle()).isEqualTo("Achat");
        assertThat(pJournalComptable.getLibelle()).isNotEqualTo("Other");
    }


    @Test
    public void testToString(){

        assertThat(journalComptables.get(0).toString()).isNotEqualTo("JournalComptable{code='PQ', libelle='Fournisseurs'}");
        assertThat(journalComptables.get(1).toString()).isEqualTo("JournalComptable{code='DZ', libelle='Vente'}");

    }


}
