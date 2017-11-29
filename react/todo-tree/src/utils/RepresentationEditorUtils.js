import {sortFields} from './../store/ui/representationEditor/Reducer';

export default class RepresentationEditorUtils {
  static findAllNotUsedSortFields(sortRules) {
    const result = Object.values(sortFields).filter(sortField => {
      const result = !RepresentationEditorUtils.isSortRulesInclude(sortRules, sortField);

      return result;
    });

    return result;
  }

  static findFirstNotUsedSortField(sortRules) {
    const result = Object.values(sortFields).find(sortField => {
      const result = !RepresentationEditorUtils.isSortRulesInclude(sortRules, sortField);
      return result;
    });

    return result;
  }

  static isSortRulesInclude(sortRules, sortField) {
    let result = sortRules.find((sortRule) => (sortRule.fieldName === sortField));

    return result !== undefined;
  }
}
