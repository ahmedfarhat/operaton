'use strict';



/**
 * @module  cam.tasklist.filter
 * @belongsto cam.tasklist
 *
 * Filters are predefined filters for tasks.
 */



define([
  'require',
  'angular',
  'moment',
  './directives/cam-tasklist-filters',
  './directives/cam-tasklist-tasks',
  './modals/cam-tasklist-filter-form',
  './controllers/filter-ctrl',
  'camunda-tasklist-ui/utils',
  'camunda-tasklist-ui/api'
], function(
  require,
  angular,
  moment,
  camTasklistFilters,
  camTasklistFilterTasks,
  camTasklistFilterForm,
  camTasklistFilterCtrl,
  utils,
  api
) {

  var filterModule = angular.module('cam.tasklist.filter', [
    utils.name,
    api.name,
    'ui.bootstrap',
    'cam.form',
    'angularMoment'
  ]);

  filterModule.controller('filterFormCtrl', camTasklistFilterForm);

  filterModule.controller('camTasklistFilterCtrl', camTasklistFilterCtrl);

  filterModule.directive('camTasklistFilters', camTasklistFilters);

  filterModule.directive('camTasklistFilterTasks', camTasklistFilterTasks);

  return filterModule;
});
