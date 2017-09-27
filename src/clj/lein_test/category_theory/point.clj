(ns lein-test.category-theory.point
  (:require [clojure.spec.alpha :as s]))

(s/def ::x double?)
(s/def ::y double?)
(s/def ::point (s/keys :req [::x ::y]))

(s/def ::g ::point)
(s/def ::p1 ::point)
(s/def ::p2 ::point)
(s/def ::triangle (s/keys :req [::g ::p1 ::p2]))

(s/def ::n int?)
(s/def ::regular-polygon (s/keys :req [::n ::p1 ::p2]))
