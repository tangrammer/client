(ns client.core
  
  )
;(:use [jayq.core :only [$ css html]])
(defn  foo
  "I don't do a whole lot."
  [x]
  (str  x "Hello, World!"))

(.log js/console (foo "juan"))
