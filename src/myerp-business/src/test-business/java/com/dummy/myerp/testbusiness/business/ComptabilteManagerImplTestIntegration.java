package com.dummy.myerp.testbusiness.business;

import com.dummy.myerp.business.impl.manager.ComptabiliteManagerImpl;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;

import com.dummy.myerp.technical.exception.FunctionalException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/dummy/myerp/testbusiness/business/bootstrapContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ComptabilteManagerImplTestIntegration extends BusinessTestCase {

    private ComptabiliteManagerImpl manager;
    private EcritureComptable vEcritureComptable;
    private JournalComptable journalComptable;



    @Test
    public void test1_InsertEcritureComptable() throws FunctionalException {

        manager = new ComptabiliteManagerImpl();
        vEcritureComptable = new EcritureComptable();
        journalComptable = new JournalComptable("AC", "Achat");
        vEcritureComptable.setJournal(journalComptable);
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setReference("AC-2021/00024");
        vEcritureComptable.setLibelle("Test_Insert");

        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),null, new BigDecimal(123),null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(411),null, null,new BigDecimal(123)));


        int sizeBeforeInsert = manager.getListEcritureComptable().size();
        manager.insertEcritureComptable(vEcritureComptable);
        assertThat(manager.getListEcritureComptable().size()).isEqualTo(sizeBeforeInsert + 1);
        assertThat(vEcritureComptable.getReference()).isEqualTo("AC-2021/00024");


    }

    @Test
    public void test2_UpdateEcritureComptable() throws FunctionalException{

        manager = new ComptabiliteManagerImpl();
        EcritureComptable ecritureComptable = manager.getListEcritureComptable().get(0);
        assertThat(ecritureComptable.getId()).isEqualTo(-1);
        ecritureComptable.setLibelle("Update_Test");
        manager.updateEcritureComptable(ecritureComptable);
        assertThat(ecritureComptable.getLibelle()).isEqualTo("Update_Test");

    }

    @Test
    public void test3_DeleteEcritureComptable(){

        manager = new ComptabiliteManagerImpl();
        int sizeBeforeDelete = manager.getListEcritureComptable().size();
        EcritureComptable ecritureComptable = manager.getListEcritureComptable().get(4);
        manager.deleteEcritureComptable(ecritureComptable.getId());
        assertThat(manager.getListEcritureComptable().size()).isEqualTo(sizeBeforeDelete -1);
    }

    @Test
    public void test4_AddReference() throws FunctionalException{
        manager = new ComptabiliteManagerImpl();
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setLibelle("libelle addReference");
        vEcritureComptable.setDate(new Date());
        journalComptable = (new JournalComptable("BQ","Banque"));
        vEcritureComptable.setJournal(journalComptable);
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(606),null, new BigDecimal(123),null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(706),null, null,new BigDecimal(123)));
        manager.addReference(vEcritureComptable);
        manager.insertEcritureComptable(vEcritureComptable);
        assertThat(vEcritureComptable.getReference()).isEqualTo("BQ-2021/00001");
    }

    @Test
    public void test5_AddReference_WhenRefExist_update(){
        manager = new ComptabiliteManagerImpl();
        EcritureComptable pEcritureComptable = manager.getListEcritureComptable().get(0);
        manager.addReference(pEcritureComptable);

    }

    @Test(expected = FunctionalException.class)
    public void test6_checkEcritureComptable_RG_6() throws FunctionalException {
        manager = new ComptabiliteManagerImpl();
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable = manager.getListEcritureComptable().get(0);
        vEcritureComptable.getJournal().setCode(manager.getListEcritureComptable().get(1).getJournal().getCode());
        vEcritureComptable.setReference(manager.getListEcritureComptable().get(1).getReference());
        manager.checkEcritureComptable(vEcritureComptable);


    }

    @Test(expected = FunctionalException.class)
    public void test7_checkEcritureComptable_RG_6_When_Ref_Exist() throws FunctionalException {
        manager = new ComptabiliteManagerImpl();
        vEcritureComptable = new EcritureComptable();
        EcritureComptable vEcritureComptable = manager.getListEcritureComptable().get(0);
        vEcritureComptable.setReference("VE-2016/00004");
        manager.checkEcritureComptable(vEcritureComptable);
        vEcritureComptable.setReference("VE-2016/00005");
        manager.checkEcritureComptable(vEcritureComptable);
    }
}
