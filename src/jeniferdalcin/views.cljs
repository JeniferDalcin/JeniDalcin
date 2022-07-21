(ns jeniferdalcin.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com :refer [at]]
   [jeniferdalcin.events :as events]
   [jeniferdalcin.routes :as routes]
   [jeniferdalcin.subs :as subs]))



;; Home Page

(defn home-title []
  (let [name (re-frame/subscribe [::subs/name])]
      [re-com/title
       :src   (at)
       :label (str @name)
       :level :level1
       :style {:justify-content "center" 
               :letter-spacing "2px"}]))

(defn self-apresentation []
  [re-com/title
    :label "A jeni é muito doidinha mas ama o Paulo muito muito."
    :style {:color "gray" 
            :justify-content "center" 
            :font-size "18px" 
            :font-weight "300" 
            :letter-spacing "1px"}])

(defn link-to-about-page []
  [re-com/md-circle-icon-button
   :md-icon-name "zmdi-face"
   :src      (at)
   :size :smaller
   :tooltip "about"
   :on-click #(re-frame/dispatch [::events/navigate :about])])

(defn link-to-daily-page []
  [re-com/md-circle-icon-button
   :md-icon-name    "zmdi-coffee"
   :src      (at)
   :size :smaller
   :tooltip "daily"
   :on-click #(re-frame/dispatch [::events/navigate :daily])])

(defn home-panel []
  [re-com/h-box
   :src      (at)
   :children [[:div.flex.flex-col
                [:img
                 {:src "img/sunflower.png"
                  :alt ""
                  :style {:max-width "50vw" :max-height "100vw"}}]]
              [:div.flex.flex-col {:style {:gap "12px" :padding "12px" :min-width "50vw" :align-self "center"}}
                [home-title]
                [self-apresentation]
                [link-to-about-page]
                [link-to-daily-page]]]])


(defmethod routes/panels :home-panel [] [home-panel])



;; About Page

(defn about-title []
  [re-com/title
   :src   (at)
   :label "This is the About Page."
   :level :level1
   :style {:justify-content "center"}])

(defn link-to-home-page []
  [re-com/md-circle-icon-button
   :md-icon-name    "zmdi-home"
   :src      (at)
   :size :smaller
   :tooltip "home"
   :on-click #(re-frame/dispatch [::events/navigate :home])])

(defn about-panel []
  [re-com/v-box
   :src      (at)
   :children [[:div.flex.flex-row
                [about-title]]
              [:div.flex.flex-row
                [link-to-daily-page]
                [link-to-home-page]]]])

(defmethod routes/panels :about-panel [] [about-panel])




;; Daily Page

(defn daily-title []
  [re-com/title
   :src   (at)
   :label "Jornada de Aprendizado"
   :level :level1
   :style {:justify-content "center"}])


(defn daily-panel []
  [re-com/v-box
   :src      (at)
   :children [[:div.flex.flex-row
                [daily-title]]      
              [:div.flex.flex-row
                [link-to-about-page]
                [link-to-home-page]]]])

       ;;[re-com/modal-panel
         ;;:child "Aqui foi o começo"]
(defmethod routes/panels :daily-panel [] [daily-panel])




;; main

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :src      (at)
     :height   "100%"
     :children [(routes/panels @active-panel)]]))
