(ns advent-of-code-2020.day3
  (:require [clojure.string :as string]
            [clojure.java.io :as io]))

(defn- get-at-pos [board pos]
  (nth (nth board (second pos)) (mod (first pos) (count (first board)))))

(defn- countTrees
  ([board delta] (countTrees board delta delta))
  ([board pos delta]
    (if (< (second pos) (count board))
      (+
        (if (= \# (get-at-pos board pos)) 1 0) (countTrees board (map + pos delta) delta))
      0)
   ))

(defn part1 [file]
  (countTrees (string/split-lines (slurp file)) [3 1]))

(defn part2 [file]
  (let [board (string/split-lines (slurp file))]
    (reduce * (map (partial countTrees board) [[1 1] [3 1] [5 1] [7 1] [1 2]]))))


(defn -main [] (let [f (io/resource "in_day3.txt")]
                 (println "Part 1:" (part1 f) "\nPart 2:" (part2 f))))