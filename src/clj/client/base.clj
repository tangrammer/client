(ns client.base
  
(:use [clojure.core.async])
  )
(defn sum [vals ch]
  (go (>! ch (reduce + vals))))

(let [vals [7 2 8 -9 4 0]
      ch (chan)]
  (go (sum (take 3 vals) ch))
  (go (sum (drop 3 vals) ch))

  (let [x (<!! ch)
        y (<!! ch)]
    (println x y (+ x y))))

(let [c1 (chan)
      c2 (chan)]
  (go (while true
        (let [[v ch] (alts! [c1 c2])]
          (println "Read" v "from" ch))))
  (go (>! c1 "hi"))
  (go (>! c2 "there")))
