(ns dots-game.ex3
  (:require
   [cljs.core.async :as async
    :refer [<! >! chan close! sliding-buffer put! alts! timeout]]
   [jayq.core :refer [$ append ajax inner css $deferred
                      when done resolve pipe on bind attr
                      offset] :as jq]
   [jayq.util :refer [log]]
   [crate.core :as crate]
   [dots-game.ex1 :refer [filter-drawing-events draw-event-capture]]
   [dots-game.ex2 :refer [render-example-board grid-unit board-size] :as board])
  (:require-macros [cljs.core.async.macros :as m :refer [go]]))

(defn -log [m]
;  (.log js/console m)
  )





(def reverse-board-position (partial - (dec board-size)))

(defn coord->dot-pos [offset {:keys [x y]}]
  (let [[x y] (map - [x y] offset [13 13])]
    (when (and (< 12 x (+ 12 grid-unit))
               (< 12 y (* board-size grid-unit)))
      (reverse-board-position (int (/ y grid-unit))))))

(defn collect-dots [draw-channel out-chan board-offset init-msg]
  (go
   (loop [last-pos nil
          msg init-msg]
     (when (= :draw (first msg))
       (let [cur-pos (coord->dot-pos board-offset (last msg))]
         (if (and (not (nil? cur-pos)) (not= cur-pos last-pos))
           (do
             (-log [:dot-pos cur-pos])
             (put! out-chan [:dot-pos cur-pos])))
         (recur (or cur-pos last-pos) (<! draw-channel)))))))


(defn check-dot [draw-channel board-offset]
  (let [ out-chan (chan)]
    (go
     (loop [msg (<! draw-channel)]
       (when (= (first msg) :draw)
         (<! (collect-dots draw-channel out-chan board-offset msg))
         (-log msg)
         (put! out-chan [:end-dots]))
       (recur (<! draw-channel))))
    out-chan
    )
  )



(defn log-process [the-chan selector]
  (go
   (loop [the-value (<! the-chan)]
     (-log "the-value")
     (.prepend ($ (str selector "-log"))
              (crate/html [:div (prn-str the-value)]))
     (recur (<! the-chan))
     ))

)


(defn log-loop [selector]
  (let [
        board-offset ((juxt :left :top) (offset ($ selector)))
        ]
    (-> (chan)
     (draw-event-capture selector)   
     (filter-drawing-events selector)
     (check-dot board-offset)
     (log-process selector)
     )
    (render-example-board selector)
    ))


(defn m-log []
                                        ;(.greet (js/Person. "Juan"))
  (let [Person (.-Person js/f) 
        juan (Person. "Juan")
        ]
    (.log js/console  (.greet juan)))
  )
