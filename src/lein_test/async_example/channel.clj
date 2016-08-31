(ns lein-test.async-example.channel
  (:require [clojure.core.async :refer [chan go >! <! <!!]]))

(defn test-channel []
  (let [c (chan)]
    (doseq [i (range 10)]
      (go
       (Thread/sleep (rand-int 1000))
       (>! c i)))
    (<!!
     (go
      (doseq [_ (range 10)]
        (println (<! c)))))))
