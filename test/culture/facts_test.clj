(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest paris-has-culture-basis
  (let [sb (facts/spec-basis "paris")]
    (is (= 9 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "paris" (:culture/municipality %)) sb))
    (is (every? #(= "FRA" (:culture/country %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-municipality-has-no-basis
  (is (nil? (facts/spec-basis "marseille")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["paris" "marseille"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["marseille"] (:missing-municipalities c)))))

(deftest by-kind-filters
  (is (= 4 (count (facts/by-kind "paris" :dish))))
  (is (= ["paris.beverage.clos-montmartre"]
         (mapv :culture/id (facts/by-kind "paris" :beverage))))
  (is (= 1 (count (facts/by-kind "paris" :craft))))
  (is (empty? (facts/by-kind "marseille" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
