(ns client.connect
  (:require [clojure.browser.repl :as repl]))

(repl/connect "http://localhost:9000/repl")
