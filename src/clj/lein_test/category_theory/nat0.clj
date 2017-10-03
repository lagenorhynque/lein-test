(ns lein-test.category-theory.nat0
  (:require [clojure.spec.alpha :as s]))

(s/def ::zero #{:zero})
(s/def ::succ (s/or :zero ::zero
                    :succ (s/keys :req [::succ])))
(s/def ::nat0 (s/or :zero ::zero
                    :succ ::succ))

(s/fdef ->nat0
        :args (s/cat :m nat-int?)
        :ret ::nat0)
(defn ->nat0
  [m]
  (if (zero? m)
    :zero
    #::{:succ (->nat0 (dec m))}))

(s/fdef <-nat0
        :args (s/cat :n ::nat0)
        :ret nat-int?)
(defn <-nat0
  [n]
  (if (= n :zero)
    0
    (inc (<-nat0 (::succ n)))))

(s/fdef mod3
        :args (s/cat :n ::nat0)
        :ret ::nat0)
(defn mod3
  [n]
  (case n
    :zero :zero
    #::{:succ :zero} #::{:succ :zero}
    #::{:succ #::{:succ :zero}} #::{:succ #::{:succ :zero}}
    (recur (::succ (::succ (::succ n))))))
