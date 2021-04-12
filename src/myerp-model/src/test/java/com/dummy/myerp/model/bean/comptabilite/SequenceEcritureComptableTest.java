package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SequenceEcritureComptableTest {

    private int annee = 2021;
    private int derniereValeur = 10;
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

        pSequenceEcritureComptable.setAnnee(2022);
        assertThat(pSequenceEcritureComptable.getAnnee()).isNotEqualTo(2016);
        assertThat(pSequenceEcritureComptable.getAnnee()).isEqualTo(2022);

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

        assertThat(sequenceEcritureComptable.toString()).isEqualTo("SequenceEcritureComptable{annee=2021, derniereValeur=10, journal comptable=JournalComptable{code='DZ', libelle='Achat'}}");

    }

}
