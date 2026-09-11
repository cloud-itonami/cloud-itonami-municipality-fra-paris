(ns ordinance.facts-test
  (:require [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest paris-has-spec-basis
  (let [sb (facts/spec-basis "paris")]
    (is (= 2 (count sb)))
    (is (every? #(re-find #"paris\.fr" (:ordinance/url %)) sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "lyon")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["paris" "lyon"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["lyon"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["paris.reglement-terrasses-etalages"]
         (mapv :ordinance/id (facts/by-topic "paris" :licensing))))
  (is (empty? (facts/by-topic "paris" :labor)))
  (is (empty? (facts/by-topic "lyon" :zoning))))
