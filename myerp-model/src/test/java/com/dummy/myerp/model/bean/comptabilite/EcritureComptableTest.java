package com.dummy.myerp.model.bean.comptabilite;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static java.sql.Date.valueOf;
import static org.assertj.core.api.Assertions.assertThat;


public class EcritureComptableTest {

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                                     .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                                                                    vLibelle,
                                                                    vDebit, vCredit);
        return vRetour;
    }

    private EcritureComptable vEcriture;

    @Before
    public void initEcritureComptable(){
        vEcriture = new EcritureComptable();

        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, "301"));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "40", "7"));

    }

    @Test
    public void isEquilibree() {

        assertThat(vEcriture.isEquilibree()).isEqualTo(true);
    }

    @Test
    public void isNotEquilibree() {

        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "1", "2"));
        assertThat(vEcriture.isEquilibree()).isEqualTo(false);
    }

    @Test
    public void getTotalDebit_test_Is_Equals_To_The_Sum_Of_TotalDebit(){
        vEcriture.getListLigneEcriture().clear();
        vEcriture.getListLigneEcriture().add(createLigne(1,"100.00",null));
        vEcriture.getListLigneEcriture().add(createLigne(1,"50","30"));
        vEcriture.getListLigneEcriture().add(createLigne(1,"25.5","29.00"));
        vEcriture.getListLigneEcriture().add(createLigne(1,"-40","89.8"));

        Assert.assertEquals(vEcriture.getTotalDebit(), BigDecimal.valueOf(100+50+25.5+(-40)).setScale(2, RoundingMode.HALF_EVEN));

    }

    @Test
    public void getTotalCredit_test_Is_Equals_To_The_Sum_Of_TotalCredit(){
        vEcriture.getListLigneEcriture().clear();
        vEcriture.getListLigneEcriture().add(createLigne(1,"100.00",null));
        vEcriture.getListLigneEcriture().add(createLigne(1,"50","30"));
        vEcriture.getListLigneEcriture().add(createLigne(1,"25","-29.00"));
        vEcriture.getListLigneEcriture().add(createLigne(1,"40","89.8"));

        Assert.assertEquals(vEcriture.getTotalCredit(), BigDecimal.valueOf(30+(-29.00)+89.8).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void test_GetterSetter_EcritureComptable(){
        EcritureComptable pEcritureComptable = new EcritureComptable();
        JournalComptable journalComptable = new JournalComptable();
        pEcritureComptable.setReference("AC");
        pEcritureComptable.setLibelle("Achat");
        pEcritureComptable.setId(2);
        Date date = new Date();
        pEcritureComptable.setDate(new Date());
        pEcritureComptable.setJournal(journalComptable);
        assertThat(pEcritureComptable.getJournal()).isEqualTo(journalComptable);
        assertThat(pEcritureComptable.getReference()).isNotEqualTo("RC");
        assertThat(pEcritureComptable.getReference()).isEqualTo("AC");
        assertThat(pEcritureComptable.getLibelle()).isNotEqualTo("Other");
        assertThat(pEcritureComptable.getLibelle()).isEqualTo("Achat");
        assertThat(pEcritureComptable.getDate()).isEqualTo(date);
        assertThat(pEcritureComptable.getId()).isNotEqualTo(1);
        assertThat(pEcritureComptable.getId()).isEqualTo(2);

    }

    @Test
    public void testToString(){

        LocalDate localDate = LocalDate.of(2020, 12, 28);
        Date date = valueOf(localDate);

        JournalComptable journalComptable = new JournalComptable();
        journalComptable.setCode("AX");
        journalComptable.setLibelle("Journal de banque");

        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setReference("AX-2020/00076");
        ecritureComptable.setDate(date);
        ecritureComptable.setId(23);
        ecritureComptable.setJournal(journalComptable);
        ecritureComptable.setLibelle("Libellé");

        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                null, new BigDecimal(123),
                null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(411),
                null, null,
                new BigDecimal(123)));

        assertThat(ecritureComptable.toString()).isEqualTo("EcritureComptable{id=23, journal=JournalComptable{code='AX', libelle='Journal de banque'}, reference='AX-2020/00076', date=2020-12-28, libelle='Libellé', totalDebit=123.00, totalCredit=123.00, listLigneEcriture=[\n" +
                "LigneEcritureComptable{compteComptable=CompteComptable{numero=401, libelle='null'}, libelle='null', debit=123, credit=null}\n" +
                "LigneEcritureComptable{compteComptable=CompteComptable{numero=411, libelle='null'}, libelle='null', debit=null, credit=123}\n" +
                "]}");

    }



}
