(ns kundel.core_test
  (:require [cljs.test :refer-macros [deftest is testing run-tests]]
            [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [kundel.mock :as mock]))

(deftest foo
  (reset! db/app-db (merge mock/test-db {}))
  (is (= 1 1)))
            