(ns lein-test.category-theory.nat1
  (:require [clojure.spec.alpha :as s]))

(s/def ::one #{:one})
(s/def ::succ (s/or :one ::one
                    :succ (s/keys :req [::succ])))
(s/def ::nat1 (s/or :one ::one
                    :succ ::succ))

(s/fdef ->nat1
        :args (s/cat :m pos-int?)
        :ret ::nat1)
(defn ->nat1
  [m]
  (if (= m 1)
    :one
    #::{:succ (->nat1 (dec m))}))

(s/fdef <-nat1
        :args (s/cat :n ::nat1)
        :ret pos-int?)
(defn <-nat1
  [n]
  (if (= n :one)
    1
    (inc (<-nat1 (::succ n)))))

(s/fdef plus
        :args (s/cat :m ::nat1
                     :n ::nat1)
        :ret ::nat1)
(defn plus
  [m n]
  (if (= n :one)
    #::{:succ m}
    #::{:succ (plus m (::succ n))}))

(s/fdef times2
        :args (s/cat :m ::nat1)
        :ret ::nat1)
(defn times2
  [m]
  (plus m m))
