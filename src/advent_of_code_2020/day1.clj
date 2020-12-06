(ns advent-of-code-2020.day1
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]))


(defn- sums-of [[n_head & n_rest]]
  (if n_head
    (concat (map (partial + n_head) n_rest) (sums-of n_rest))
    []))

(defn- prods-of [[n_head & n_rest]]
  (if n_head
    (concat (map (partial * n_head) n_rest) (prods-of n_rest))
    []))

(defn- sums-and-prods [ns]
  (map vector (sums-of ns) (prods-of ns)))

(defn- sums3-of [[n_head & n_rest]]
  (if n_head
    (concat (map (partial + n_head) (sums-of n_rest)) (sums3-of n_rest))
    []))

(defn- prods3-of [[n_head & n_rest]]
  (if n_head
    (concat (map (partial * n_head) (prods-of n_rest)) (prods3-of n_rest))
    []))

(defn- sums-and-prods3 [ns]
  (map vector (sums3-of ns) (prods3-of ns)))

(defn part1 [file] (some #(if (= (first %) 2020) (second %))
                         (sums-and-prods
                           (edn/read-string (str "[" (slurp file) "]")))))

(defn part2 [file] (some #(if (= (first %) 2020) (second %))
                                  (sums-and-prods3
                                    (edn/read-string (str "[" (slurp file) "]")))))

(defn -main [] (let [f (io/resource "in_day1.txt")]
                (println "Part 1:" (part1 f) "\nPart 2:" (part2 f))))