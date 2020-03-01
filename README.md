# Clojure-web-app

Informacioni sistem "REGIS" (Registar informacionih sistema) predstavlja informacioni sistem za evidenciju i pracenje svih informacionih sistema u upotrebi firme.

## Detalji o projektu
Projekat je pisan u Clojure (Leiningen) korisceno je:

* Jetty - web server
* Ring - biblioteka za mapiranje HTTP zahteva i odgovora u Clojure tipove podataka
* Hiccup - biblioteka za generisanje HTML-a
* Compojure - biblioteka za rutiranje
* MySQL - baza podataka za persistentnost podataka
* Bootstrap - korisnicki interfejs
* Jquery - interakcija sa korisnikom


## Instrukcije

Ove instrukcije vode do kopiranja projekta na lokalnoj masini i pokretanje aplikacije u "localhost".

### Neophodan softver za pokretanje
*MYSQL server
*Okruzenje za upravljanje Clojure (leiningen) projektom

## Pokretanje aplikacije

Prvi korak je pravljenje baze. Izvrsiti skriptu "baza.sql" koja se nalazi u folderu MySql.
Skinuti projekat sa GitHub-a. Ukoliko zelite da promenite parametre pristupa bazi, menjaju se u fajlu
db.cljr (trenutni kredencijali su "root" "root")
Nakon pokretanja aplikacije, ukucati adresu u internet pretrazivacu "http://localhost:8006/pocetna"

## Demonstracija
Video  demonstracija rada aplikacije, na sledecem linku:
https://drive.google.com/file/d/1Gxl7JK6RFX94Qn-LHR4xD6Td90rvIls5/view?usp=sharing

## Literatura

 * http://clojure-doc.org/articles/tutorials/basic_web_development.html
 * https://practicalli.github.io/clojure-webapps/hiccup/
 

