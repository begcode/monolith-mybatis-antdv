import qs from 'qs';
import { get } from 'lodash-es';
import { defHttp } from '@/utils/http/axios';
import buildPaginationQueryOpts from '@/utils/jhipster/sorts';
import { PageRecord } from '@/models/baseModel';
import { useMethods } from '@/hooks/system/useMethods';
import { IAuthority } from '@/models/system/authority.model';

const apiUrl = '/api/authorities';

export default {
  // begcode-please-regenerate-this-file 如果您不希望重新生成代码时被覆盖，将please修改为don't ！！！

  find(id: number): Promise<IAuthority> {
    return defHttp.get({ url: `${apiUrl}/${id}` });
  },

  retrieve(paginationQuery?: any): Promise<PageRecord<IAuthority>> {
    const options = buildPaginationQueryOpts(paginationQuery);
    return defHttp.get({ url: apiUrl, params: qs.stringify(options, { arrayFormat: 'repeat' }) });
  },

  stats(queryParams?: any): Promise<any> {
    const options = buildPaginationQueryOpts(queryParams);
    return defHttp.get({ url: `${apiUrl}/stats`, params: qs.stringify(options, { arrayFormat: 'repeat' }) });
  },

  exist(queryParams?: any): Promise<Boolean> {
    if (!queryParams.hasOwnProperty('id.aggregate.count') && get(queryParams, 'id.aggregate.count')) {
      queryParams['id.aggregate.count'] = true;
    }
    const options = buildPaginationQueryOpts(queryParams);
    return new Promise((resolve, reject) => {
      defHttp
        .get({ url: `${apiUrl}/stats?`, params: qs.stringify(options, { arrayFormat: 'repeat' }) })
        .then(res => {
          resolve(res && res[0] && res[0]['id_count'] > 0);
        })
        .catch(err => reject(err));
    });
  },

  updateSortValue(
    id: number,
    type: 'DROP' | 'STEP' | 'VALUE',
    newSortValue: number,
    beforeId: number,
    afterId: number,
    authorityCriteria: any,
  ): Promise<Boolean> {
    return defHttp.put({
      url: `${apiUrl}/sort-value/${id}/${type}?newSortValue=${newSortValue || ''}&beforeId=${beforeId || ''}&afterId=${afterId || ''}`,
      params: authorityCriteria,
    });
  },

  delete(id: number): Promise<any> {
    return defHttp.delete({ url: `${apiUrl}/${id}` });
  },

  deleteByIds(ids: number[]): Promise<any> {
    return defHttp.delete({ url: `${apiUrl}` + `?${qs.stringify({ ids }, { arrayFormat: 'repeat' })}` });
  },

  tree(paginationQuery?: any): Promise<PageRecord<IAuthority[]>> {
    const options = buildPaginationQueryOpts(paginationQuery);
    return defHttp.get({ url: `${apiUrl}/tree`, params: qs.stringify(options, { arrayFormat: 'repeat' }) });
  },

  treeByParentId(parentId: number): Promise<IAuthority[]> {
    return defHttp.get({ url: `${apiUrl}/${parentId}/tree` });
  },

  create(authority: IAuthority): Promise<IAuthority> {
    return defHttp.post({ url: `${apiUrl}`, params: authority });
  },

  update(authority: IAuthority, batchIds?: number[], batchFields?: String[]): Promise<IAuthority> {
    let queryParams = '';
    if (batchIds && batchFields) {
      queryParams = qs.stringify({ batchIds, batchFields }, { arrayFormat: 'repeat' });
    }
    return defHttp.put({ url: `${apiUrl}/${authority.id}?${queryParams}`, params: authority });
  },

  updateRelations(otherEntityIds: String[], relationshipName: String, relatedIds: number[], operateType: String): Promise<Boolean> {
    const queryParams = qs.stringify({ otherEntityIds, relatedIds, relationshipName }, { arrayFormat: 'repeat' });
    return defHttp.put({ url: `${apiUrl}/relations/${operateType}`, params: queryParams });
  },

  /**
   * 导出xls
   * @param fileName 文件名
   * @param paginationQuery 分页参数
   * @param isXlsx 是否导出xlsx
   */
  exportExcel(fileName, paginationQuery?: any, isXlsx = false) {
    const { handleExportXls, handleExportXlsx } = useMethods();
    const options = buildPaginationQueryOpts(paginationQuery);
    if (isXlsx) {
      return handleExportXlsx(fileName, `${apiUrl}/export`, options);
    } else {
      return handleExportXls(fileName, `${apiUrl}/export`, options);
    }
  },

  /**
   * 导入xls
   * @param file 导入的文件
   * @param success 成功回调
   */
  importExcel(file, success) {
    const { handleImportXls } = useMethods();
    return handleImportXls(file, `${apiUrl}/import`, success);
  },
  // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove
};
