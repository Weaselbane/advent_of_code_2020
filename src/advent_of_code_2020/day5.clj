(ns advent-of-code-2020.day5
  (:require [clojure.string :as string]
            [clojure.java.io :as io]
            [clojure.math.numeric-tower :as math]))

(defn- parse-seat [[ch & rest]]
  (if ch
    (+ (* (math/expt 2 (count rest)) (if (string/includes? "BR" (str ch)) 1 0)) (parse-seat rest))
    0))

(defn- l-contains? [es e]
  (some #{e} es))

(defn- find-missing [ids]
  (+ 1 (some #(if (and (not (l-contains? ids (+ % 1))) (l-contains? ids (+ % 2))) %) ids)))

(defn part1 [file]
  (apply max (map parse-seat (string/split-lines (slurp file)))))

(defn part2 [file]
  (find-missing (map parse-seat (string/split-lines (slurp file)))))


(defn -main [] (let [f (io/resource "in_day5.txt")]
                 (println "Part 1:" (part1 f) "\nPart 2:" (part2 f))))