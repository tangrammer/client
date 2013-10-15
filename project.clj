(defproject client "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"sonatype-staging"
                 "https://oss.sonatype.org/content/groups/staging/"}
  
  :dependencies [
                 [org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1934"]
                 [jayq "2.4.0"]
                 ]
  :plugins [[lein-cljsbuild "0.3.2"]]
  :cljsbuild {:builds
   [
    {:id "base"
     :source-paths ["src"
                    ]
     :compiler {:optimizations :whitespace
                   ; :externs ["externs/jquery.min.js"]
                :pretty-print false
                :output-to "example.js"}}
    {:id "base-adv"
     :source-paths ["src"
                    ]
     :compiler {:optimizations :advanced
;                :externs ["externs/jquery.min.js"]
                :pretty-print false
                :output-to "example.js"}}
    ]}
  )
