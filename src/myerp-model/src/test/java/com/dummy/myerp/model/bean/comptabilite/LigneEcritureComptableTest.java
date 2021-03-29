package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class LigneEcritureComptableTest {

    @Test
    public void test_GetterSetter_LigneEcritureComptable(){
        LigneEcritureComptable vLigneEcritureComptable = new LigneEcritureComptable();

        vLigneEcritureComptable.setLibelle("test_Libelle");
        assertThat(vLigneEcritureComptable.getLibelle()).isNotEqualTo("test");
        assertThat(vLigneEcritureComptable.getLibelle()).isEqualTo("test_Libelle");

        vLigneEcritureComptable.setDebit(new BigDecimal("123"));
        assertThat(vLigneEcritureComptable.getDebit()).isEqualTo("123");
        assertThat(vLigneEcritureComptable.getDebit()).isNotEqualTo("124");

        vLigneEcritureComptable.setCredit(new BigDecimal("123"));
        assertThat(vLigneEcritureComptable.getCredit()).isEqualTo("123");
        assertThat(vLigneEcritureComptable.getCredit()).isNotEqualTo("124");

        CompteComptable vCompteComptable = new CompteComptable();

        vLigneEcritureComptable.setCompteComptable(vCompteComptable);
        assertThat(vLigneEcritureComptable.getCompteComptable()).isEqualTo(vCompteComptable);
    }

    @Test
    public void testToString(){

        CompteComptable compteComptable = new CompteComptable();
        compteComptable.setLibelle("Investisseurs");
        compteComptable.setNumero(123);

        LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable();
        ligneEcritureComptable.setCredit(new BigDecimal("456.98"));
        ligneEcritureComptable.setCompteComptable(compteComptable);
        ligneEcritureComptable.setLibelle("Audit");
        assertThat(ligneEcritureComptable.toString()).isNotEqualTo("LigneEcritureComptable{compteComptable=CompteComptable{numero=325, libelle='Fournisseurs'}, libelle='Paiement', debit=null, credit=123.98}");
        assertThat(ligneEcritureComptable.toString()).isEqualTo("LigneEcritureComptable{compteComptable=CompteComptable{numero=123, libelle='Investisseurs'}, libelle='Audit', debit=null, credit=456.98}");

    }


}
