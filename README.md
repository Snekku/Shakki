# Shakki sovellus, Joni Piispanen, joempiis@student.jyu.fi, Tietotekniikka, Tietotekniikan ohjelmointityö, 1.5.2020

Sovelluksessa kaytetaan osoitteesta: https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent haettuja kuvakkeita, jotka ovat osoitteesta https://creativecommons.org/licenses/by-sa/3.0/deed.en loytyvan lisenssin alla jaettu.

Tietokoneohjelma, jossa voi pelata shakkia tietokonetta vastaan graafisen käyttöliittymän avulla. Ohjelmoin projektin käyttäen javaa ja graafisen käyttöliittymän käyttäen swingiä.

Ohjelmoin työn käyttäen olio ohjelmoinnin periaatteita. Ohjelman rakenne näyttää alustavasti seuraavanlaiselta.

Shakki.java on pääasiallinen controller luokka, joka myös sisältää pääohjelman(main).

Kayttoliittyma.java sisaltaa kayttoliittyman kaikkine komponentteineen ja lisaksi shakkilaudan esityksen. Ajan salliessa tulen lisaamaan tahan painikkeet, jotka mahdollistavat ylimaaraisia toimintoja, kuten pelikellojen asettamisen, puolten vaihtamisen ja tietokoneen arvion esittämisen, pelin taman hetkisesta tilasta.

Tekoaly.java tulee sisaltamaan tietokonevastustajan lahdekoodin. Alustavasti suunnitelmana ohjelmoida minimax-algoritmi, alfa-beta pruunauksella. Ajan salliessa paivitan tata lisaominaisuuksilla vahvemmaksi.

Nappula.java on abstrakti luokka, joka sisaltaa nappuloiden yleis/yhtenaisen rakenteen, jota nappuloiden omat luokat taydentavat omien toiminnallisuuksiensa mukaan.

Sotilas.java, Torni.java, Ratsu.java, Lahetti.java, Kuningas.java ja Kuningatar.java tulevat sisaltamaan eri nappuloiden toiminnallisuudet, laillisista siiroista, erikoisempiin ominaisuuksiin, kuten sotilaan korotukseen.

Aikataulu tulee alustavasti nayttamaan seuraavanlaiselta.

Suunnittelu 12h.

Graafinen kayttoliittyma, nappuloiden liikuttaminen hiirella 24h.

Lailliset siirrot nappuloille, syominen, korotus 20h.

Kuninkaan turvallisuus, erikoisemmat saannot, kuten en passant, linnoitus, yms. 16h.

Tekoalyn toiminnallisuus 24h.

Graafisen kayttoliittyman lisaominaisuudet 16h.

Lopullinen testaaminen, debuggaus 16h.

Ajan salliessa, lisaksi tekoalyn parantaminen ?h.
