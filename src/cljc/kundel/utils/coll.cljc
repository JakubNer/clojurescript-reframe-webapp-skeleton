(ns kundel.utils.coll
  (:require [debux.cs.core :refer-macros [clog dbg break]]))

(defn vec-remove
  "remove elem in coll returning a vector"
  [coll pos]
  (vec (concat (subvec coll 0 pos) (subvec coll (inc pos)))))

(defn set-toggle
  "toggle an item in a set:  add if not there else remove."
  [coll item]
  (if (some #{item} coll)
    (disj coll item)
    (into #{} (conj coll item))))
