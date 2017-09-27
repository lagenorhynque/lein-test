(ns lein-test.category-theory.car
  (:require [clojure.spec.alpha :as s]))

(s/def ::company string?)
(s/def ::style string?)
(s/def ::production integer?)
(s/def ::car (s/keys :req [::company ::style ::production]))
