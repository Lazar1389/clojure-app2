(defproject clojure-app "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring                "1.7.1"]
                 [compojure "1.6.1"]
                 [hiccup "1.0.5"]
                 [org.clojure/java.jdbc "0.7.11" ]
                 [mysql/mysql-connector-java "8.0.18"]
                 [com.microsoft.sqlserver/mssql-jdbc "8.1.1.jre13-preview"]
                 ]
  :repl-options {:init-ns clojure-app.core}

  :profiles {:dev
             {:main todo-list.core/-dev-main}}
  )

