(defproject client "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"sonatype-staging"
                 "https://oss.sonatype.org/content/groups/staging/"}
  :repl-options {:nrepl-miDdleware [cemerick.piggieback/wrap-cljs-repl] }
  :plugins [[com.cemerick/austin "0.1.1"]]
  :injections [(require '[cljs.repl.browser :as brepl]
                        '[cemerick.piggieback :as pb])
               (defn browser-repl []
                 (pb/cljs-repl :repl-env (brepl/repl-env :port 9000)))
               ]

  :dependencies [
                 [org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1934"]
                 [jayq "2.4.0"]
                 [com.cemerick/piggieback "0.1.0"]
                 ]
  :plugins [[lein-cljsbuild "0.3.2"]]
  :cljsbuild {:builds
   [
    {:id "base"
     :source-paths ["src"
                    ]
     :compiler {:optimizations :whitespace
                    :externs ["site/externs/jquery.min.js"]
                :pretty-print true
                :output-to "site/example.js"}}
    {:id "base-adv"
     :source-paths ["src"
                    ]
     :compiler {:optimizations :advanced
                :externs ["site/externs/jquery.min.js"]
                :pretty-print false
                :output-to "site/example.js"}}
    ]}
  )
