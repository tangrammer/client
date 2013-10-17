(ns client.core
  (:require
   [jayq.core :refer [$ css prepend append ajax inner $deferred when done resolve pipe on] :as jq]
   [cljs.core.async :as async
    :refer [<! >! >!!  chan close! sliding-buffer put! alts!]]

   )
  (:require-macros [cljs.core.async.macros :as m :refer [go]])

  )

(def c1 (chan))
(go (while true
        (let [v (<! c1)]
          (appendea v))))
(defn get-body [] ($ :body))

(defn greeny [] (-> (get-body)
     (css {:background "yellow"})
     (html "guau miao2!")) nil)
(defn colorea [color]
  (-> (get-body) (css {:background color})))

(defn appendea [message]
  (-> (get-body) (prepend (str message "<br>")))
  )

(defn  foo
  "I don't do a whole lot."
  [x]
  (str  x "Hello, World!"))

(defn log []  (.log js/console (foo "juanito")))

(defn write [mes]
  (go (>! c1 mes)))
