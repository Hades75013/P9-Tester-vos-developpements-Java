package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.util.Date;

import com.dummy.myerp.model.bean.comptabilite.*;
import org.junit.Before;
import org.junit.Test;
import com.dummy.myerp.technical.exception.FunctionalException;

import static org.assertj.core.api.Assertions.assertThat;


public class ComptabiliteManagerImplTest {

    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();

    private EcritureComptable vEcritureComptable;

    private LigneEcritureComptable ligneEcritureComptableDebit;

    private LigneEcritureComptable ligneEcritureComptableCredit;

    private JournalComptable journalComptable;


    @Before
    public void initComptabliteMangerImpl(){

        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setId(1);
        journalComptable = new JournalComptable("AC", "Achat");
        vEcritureComptable.setJournal(journalComptable);
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setReference("AC-2020/00001");
        vEcritureComptable.setLibelle("Libelle");

        ligneEcritureComptableCredit = new LigneEcritureComptable(new CompteComptable(1),null, new BigDecimal(123),null);
        ligneEcritureComptableDebit = new LigneEcritureComptable(new CompteComptable(2), null, null, new BigDecimal(123));

    }

    @Test
    public void addReference(){
        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(2020,15,vEcritureComptable.getJournal());
        SequenceEcritureComptable sequenceEcritureComptable1 = new SequenceEcritureComptable(2019,12,vEcritureComptable.getJournal());

        assertThat(manager.reference(sequenceEcritureComptable)).isEqualTo("AC-2020/00015");
        assertThat(manager.reference(sequenceEcritureComptable)).isNotEqualTo("AD-2020/00015");

        assertThat(manager.reference(sequenceEcritureComptable1)).isEqualTo("AC-2019/00012");
        assertThat(manager.reference(sequenceEcritureComptable1)).isNotEqualTo("AC-2020/00015");
    }


    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnit() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(ligneEcritureComptableDebit);
        vEcritureComptable.getListLigneEcriture().add(ligneEcritureComptableCredit);
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }


    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitViolation() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG2() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(1234)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }


    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnit_RG3_DeuxLignesDebit() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        manager.checkEcritureComptableUnit(vEcritureComptable);

    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnit_RG3_UneSeulLigneEcriture() throws Exception{
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null,
                null));

        manager.checkEcritureComptableUnit(vEcritureComptable);

        vEcritureComptable.getListLigneEcriture().clear();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null,
                new BigDecimal(123)));
        manager.checkEcritureComptableUnit(vEcritureComptable);

        vEcritureComptable.getListLigneEcriture().clear();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        manager.checkEcritureComptableUnit(vEcritureComptable);

    }



    @Test
    public void checkEcritureComptableUnitRG4_Debit(){
        vEcritureComptable.setJournal( journalComptable );
        vEcritureComptable.setReference("AC-2020/00001");
        vEcritureComptable.setDate( new Date() );
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(5),
                "Facture 6", null,
                new BigDecimal("123.56")));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4),
                "Facture 5", new BigDecimal("-123.56"), null));


        assertThat(vEcritureComptable.getTotalDebit()).isEqualTo(BigDecimal.valueOf(-123.56));
        assertThat(vEcritureComptable.getTotalDebit()).isNotEqualTo(BigDecimal.valueOf(123.56));

    }

    @Test
    public void checkEcritureComptableUnitRG4_Credit(){
        vEcritureComptable.setJournal( journalComptable );
        vEcritureComptable.setReference("AC-2020/00001");
        vEcritureComptable.setDate( new Date() );
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(5),
                "Facture 6", null,
                new BigDecimal("-123.56")));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4),
                "Facture 5", new BigDecimal("123.56"), null));


        assertThat(vEcritureComptable.getTotalCredit()).isEqualTo(BigDecimal.valueOf(-123.56));
        assertThat(vEcritureComptable.getTotalCredit()).isNotEqualTo(BigDecimal.valueOf(123.56));


    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG5_Annee() throws Exception{
        vEcritureComptable.setJournal( journalComptable );
        vEcritureComptable.setReference("AC-2020/00001");
        vEcritureComptable.setDate( new Date() );
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                "Facture 1", new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                "Facture 2", null,
                new BigDecimal(123)));
        manager.checkEcritureComptableUnit(vEcritureComptable);

        vEcritureComptable.setReference("AC-2019/00001");
        manager.checkEcritureComptableUnit(vEcritureComptable);

    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG5_Reference() throws Exception{
        vEcritureComptable.setJournal( journalComptable );
        vEcritureComptable.setReference("AC-2020/00001");
        vEcritureComptable.setDate( new Date() );
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4),
                "Facture 5", new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(5),
                "Facture 6", null,
                new BigDecimal(123)));
        manager.checkEcritureComptableUnit(vEcritureComptable);

        vEcritureComptable.setReference("AD-2020/00001");
        manager.checkEcritureComptableUnit(vEcritureComptable);

    }


    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG7_2ChiffresApresVirgule() throws Exception{
        vEcritureComptable.setJournal( journalComptable );
        vEcritureComptable.setReference("AC-2020/00001");
        vEcritureComptable.setDate( new Date() );
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(5),
                "Facture 1", null,
                new BigDecimal("123.456")));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4),
                "Facture 2", new BigDecimal("123.456"),
                null));


        manager.checkEcritureComptableUnit(vEcritureComptable);


    }





}
