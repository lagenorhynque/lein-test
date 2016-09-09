(ns lein-test.core
  (:require [lein-test.stm-example.stm-demo :as s])
  (:gen-class))

(defn -main []
  (println "Hello, World!")
  (println (s/exec 3)))
