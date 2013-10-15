(ns client.core
 (:use [jayq.core :only [$ css html]]) 
  )
(defn get-body [] ($ :body))

(defn greeny [] (-> (get-body)
     (css {:background "blue"})
     (html "guau!")) nil)
;
(defn  foo
  "I don't do a whole lot."
  [x]
  (str  x "Hello, World!"))

(defn log []  (.log js/console (foo "juanito")))
