(ns client.core
 (:use [jayq.core :only [$ css html append]]) 
  )
(defn get-body [] ($ :body))

(defn greeny [] (-> (get-body)
     (css {:background "yellow"})
     (html "guau miao2!")) nil)
(defn colorea [color]
  (-> (get-body) (css {:background color})))

(defn appendea [message]
  (-> (get-body) (append message))
  )

(defn  foo
  "I don't do a whole lot."
  [x]
  (str  x "Hello, World!"))

(defn log []  (.log js/console (foo "juanito")))
