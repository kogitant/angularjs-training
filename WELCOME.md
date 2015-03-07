# Tervetuloa Goforen AngularJS-koulutukseen!

Löydät nyt ja jatkossa täältä Githubista kaikki koulutukseen liittyvät materiaalit.

Jotta koulutus sujuisi ripeästi, pyydämme, että valmistelisit käyttöösi ennakolta seuraavanlaisen kehitysympäristön.

## Node.js, npm, git ja bower asennettuina

Linkit asennusohjeisiin/paketteihin:

- Node.js (tai io.js) ja npm: <http://nodejs.org/>
- Git: <http://git-scm.com/>
- Bower: <http://bower.io/>

Tarkista, että sovellukset toimivat komentoriviltä ja niistä on asennettuna
tuoreet versiot.

Esimerkki toimivasta konfiguraatiosta:

    > node --version
    v0.10.33

    > npm --version
    v1.4.28

    > git --version
    git version 1.9.5

    > bower --version
    1.3.12

## Editorin asennus

Vaihtoehtoisesti varmista, että käyttämässäsi editorissa/IDE:ssä on JavaScript ja nodejs-tuki aktivoituna. Huomaathan, että koulutuksen tarpeisiin riittää komentorivityökalujen rinnalle varsin hyvin esim. ilmainen atom.io (<https://atom.io/>).

### IntelliJ Idea

- Ideassa on JavaScript-tuki mukana vakiona. Voit halutessasi asentaa käyttöön myös node.js-tuen laajennuksena (<https://www.jetbrains.com/idea/features/nodejs.html>).

###  Eclipse

- Asenna ja ota käyttöön Nodeclipse (<http://www.nodeclipse.org/updates/>).

### NetBeans

- NetBeans 8 sisältää valmiiksi asennettuna tarvittavan JavaScript, node.js yms. tuen
- Vanhempiin NetBeans-versioihin voi tarvittaessa asentaa käsin NodeJS-laajennuksen (<http://plugins.netbeans.org/plugin/36653/nodejs>)

## Tee kopio angular-seed -projektista

Tee itsellesi työhakemistoon kopio angular-seed -projektista:

<https://github.com/angular/angular-seed>

Tarkista, että projektin kehitysympäristö käynnistyy komennolla:

    npm start

- Jos kaikki toimii hyvin, kehityspalvelin näkyy selaimessa osoitteella <http://localhost:8000>
- **Huom!** itse softa löytyy osoitteesta: <http://localhost:8000/app/>
- Jos kehitysympäristö ei käynnisty, tarkista, että yllämainitut sovellukset
toimivat komentoriviltä.

