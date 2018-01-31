(ns kundel.runner
  (:require [cljs.test :refer-macros [run-all-tests]]
            [kundel.core_test]
            [kundel.mock :as mock]
            [re-frame.db :as db]))

(enable-console-print!)

(reset! db/app-db (merge mock/test-db {:state {:categories [:a :b]}}))

(defn ^:export run []
  (run-all-tests #"kundel.*_test"))
  
