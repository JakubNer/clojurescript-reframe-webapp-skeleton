(ns kundel.scratch
  (:require [debux.cs.core :refer-macros [clog dbg break]]))

;; scratch, not real tests

(def foo {:a {:y [{:m nil} {:l nil}], :x [{:l nil :t 1} {:l nil :t 2} {:m nil}]}, :b {:x [{:l nil :t 2}]}})

(def users '(:a :b))

(let [addresses
       (apply concat
         (for [user users
               :let [xs (get-in foo [user :x])]]
           (for [x (map-indexed vector xs)
                 :when (= (:t (second x)) 2)]
             [user :x (first x)])))]
  (print addresses))

