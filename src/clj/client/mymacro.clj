(ns client.mymacro)
(defmacro eq1 [actual expected] 
            (= expected actual))
