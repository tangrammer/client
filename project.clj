(defproject client "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"sonatype-staging"
                 "https://oss.sonatype.org/content/groups/staging/"}
  :source-paths ["src"]

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl] }


  :dependencies [
                 [org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1847"]
                 [jayq "2.4.0"]

                 ]

  

  :profiles {:dev {:repl-options {}
                   :plugins [[com.cemerick/austin "0.1.0"]
                             [lein-cljsbuild "0.3.2"]]
                   :cljsbuild {:builds [{:id "base"
                                         :source-paths ["src"
                                                        ]
                                         :compiler {:optimizations :whitespace
                                                    :externs ["site/externs/jquery.min.js"]
                                                    :pretty-print true
                                                    :output-to "site/example-bis.js"}}]}}})





