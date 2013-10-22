(ns client.core
  (:require
   [jayq.core :refer [$ css prepend append ajax inner $deferred when done resolve pipe on position] :as jq]
   [cljs.core.async :as async
    :refer [<! >! >!!  chan close! sliding-buffer put! alts!]]

   )
  (:require-macros [cljs.core.async.macros :as m :refer [go]]
                   [client.mymacro :as mm :refer [eq1 eqq1]])

  )

(comment
  <script>

var oHead = document.getElementsByTagName('HEAD').item(0);

var oScript= document.createElement("script");

oScript.type = "text/javascript";

oScript.src="other.js";

oHead.appendChild( oScript);

</script>
)
(defn add-script-to-head []
  (let [the-head js/document.head
        the-script (.createElement js/document "script")
        the-script-value "console.log(\"The script was run\");"
        ]
                                        ;(set! (.-type the-script) "text/javascript")
                                        ;(set! (.-src the-script) "url_file")
    (set! (.-innerHTML the-script) the-script-value)  
    (.appendChild the-head the-script)
    ))

(defn data-from-event [event]
  (-> event .-currentTarget $ position))

(defn click-chan [selector msg-name]
  (let [rc (chan)]
    (on ($ "body") :click selector {}
        (fn [e]
          (jq/prevent e)
          (put! rc [msg-name (data-from-event e)])))
    rc))


(def click-div-chan (click-chan "#the-div" :you-have-clicked-on-the-div))

(go (while true
        (let [v (<! click-div-chan)]
          (appendea v))))

(defn using-macros []
  (appendea (eq1 1 1)))

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
