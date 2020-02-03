(ns clojure-app.db
  ( :require  [clojure.java.jdbc :as sql]
  ))




(def mysql-db {
               :subprotocol "mysql"
               :subname     "//localhost/clojure"
               :user        "root"
               :password    "root"})



(defn dodaj-novi
  [x y z c v b]
  (let [results (sql/insert! mysql-db :regis {:NazivIS x :FazaZivotnogCiklusa y :Oblast z :Tip c :Nosilac v :OperativniSistem b}) ]
    (assert (= (count results) 1))
    (first results)
    ))


(defn get-is
  [is-id]
  (let [results (sql/query mysql-db
                            ["select * from regis where id = ?" is-id])]
    (assert (= (count results) 1))
    (first results)))

(defn select-all [req]
(sql/query mysql-db
           ["SELECT * FROM regis"]
           ))

(defn obrisi [id]
  (sql/delete! mysql-db
             :regis ["ID = ?" id]))
(comment
(defn add-location-to-db
  [x y]
  (let [results (jdbc/insert! db-spec :locations {:x x :y y})]
    (assert (= (count results) 1))
    (first (vals (first results)))))
)

(sql/insert! mysql-db
             :zaposleni {:Ime "Imee" :Prezime "Prezime" :jmbg "221231"})


(sql/delete! mysql-db
             :zaposleni ["Prezime = ? " "Prezime"])

(sql/update! mysql-db
             :zaposleni
             {:ime 40}
             ["prezime = ? " "Jovic"])