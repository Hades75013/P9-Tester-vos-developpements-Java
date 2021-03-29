package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SequenceEcritureComptableTest {

    private int annee = 2020;
    private int derniereValeur = 20;
    private JournalComptable vJournalComptable = new JournalComptable("DZ","Achat");
    private SequenceEcritureComptable vSequenceEcritureComptable = new SequenceEcritureComptable(annee, derniereValeur, vJournalComptable);


    @Test
    public void test_ConstructeurSequenceEcritureComptable(){
        assertThat(vSequenceEcritureComptable.getAnnee()).isEqualTo(annee);
        assertThat(vSequenceEcritureComptable.getDerniereValeur()).isEqualTo(derniereValeur);
        assertThat(vSequenceEcritureComptable.getJournalComptable()).isEqualTo(vJournalComptable);
    }

    @Test
    public void test_GetterSetter_SequenceEcritureComptable(){
        SequenceEcritureComptable pSequenceEcritureComptable = new SequenceEcritureComptable();

        pSequenceEcritureComptable.setJournalComptable(vJournalComptable);
        assertThat(pSequenceEcritureComptable.getJournalComptable()).isEqualTo(vJournalComptable);

        pSequenceEcritureComptable.setAnnee(2020);
        assertThat(pSequenceEcritureComptable.getAnnee()).isNotEqualTo(2016);
        assertThat(pSequenceEcritureComptable.getAnnee()).isEqualTo(2020);

        pSequenceEcritureComptable.setDerniereValeur(24);
        assertThat(pSequenceEcritureComptable.getDerniereValeur()).isNotEqualTo(90);
        assertThat(pSequenceEcritureComptable.getDerniereValeur()).isEqualTo(24);
    }

    @Test
    public void testToString() {

        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();
        sequenceEcritureComptable.setDerniereValeur(derniereValeur);
        sequenceEcritureComptable.setAnnee(annee);
        sequenceEcritureComptable.setJournalComptable(vJournalComptable);

        assertThat(sequenceEcritureComptable.toString()).isEqualTo("SequenceEcritureComptable{annee=2020, derniereValeur=20, journal comptable=JournalComptable{code='DZ', libelle='Achat'}}");

    }

}
