(ns advent-of-code-2020.day6
  (:require [clojure.string :as string]
            [clojure.java.io :as io]
            [clojure.set :as set]))

(defn- distinct-alpha [s]
  (distinct (filter #(Character/isLetter %) s)))

(defn- all-answered [ss]
  (apply set/intersection (map set ss)))

(defn part1 [file]
  (reduce + (map (comp count distinct-alpha) (string/split (slurp file) #"\r?\n\r?\n"))))

(defn part2 [file]
  (reduce + (map (comp count all-answered) (map string/split-lines (string/split (slurp file) #"\r?\n\r?\n")))))


(defn -main [] (let [f (io/resource "in_day6.txt")]
                 (println "Part 1:" (part1 f) "\nPart 2:" (part2 f))))